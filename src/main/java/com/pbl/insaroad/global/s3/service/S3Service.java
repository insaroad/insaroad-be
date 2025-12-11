/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.global.s3.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pbl.insaroad.global.exception.CustomException;
import com.pbl.insaroad.global.s3.config.S3Config;
import com.pbl.insaroad.global.s3.dto.S3Response;
import com.pbl.insaroad.global.s3.entity.PathName;
import com.pbl.insaroad.global.s3.exception.S3ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

  private final AmazonS3 amazonS3;
  private final S3Config s3Config;

  public S3Response uploadImage(PathName pathName, MultipartFile file) {

    String imgUrl = uploadFile(pathName, file);

    return S3Response.builder().imageUrl(imgUrl).build();
  }

  public String uploadFile(PathName pathName, MultipartFile file) {

    validateFile(file);

    String keyName = createKeyName(pathName);

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    metadata.setContentType(file.getContentType());

    try {
      amazonS3.putObject(
          new PutObjectRequest(s3Config.getBucket(), keyName, file.getInputStream(), metadata));
      return amazonS3.getUrl(s3Config.getBucket(), keyName).toString();
    } catch (Exception e) {
      log.error("S3 upload 중 오류 발생", e);
      throw new CustomException(S3ErrorCode.FILE_SERVER_ERROR);
    }
  }

  public String createKeyName(PathName pathName) {

    return switch (pathName) {
          case ELEMENT -> s3Config.getElementPath();
          case OTHER -> s3Config.getOtherPath();
        }
        + '/'
        + UUID.randomUUID();
  }

  public void deleteFile(String keyName) {
    existFile(keyName);

    try {
      amazonS3.deleteObject(new DeleteObjectRequest(s3Config.getBucket(), keyName));
    } catch (Exception e) {
      log.error("S3 upload 중 오류 발생", e);
      throw new CustomException(S3ErrorCode.FILE_SERVER_ERROR);
    }
  }

  public List<String> getAllFiles(PathName pathName) {
    String prefix =
        switch (pathName) {
          case ELEMENT -> s3Config.getElementPath();
          case OTHER -> s3Config.getOtherPath();
        };

    try {
      return amazonS3
          .listObjectsV2(
              new ListObjectsV2Request().withBucketName(s3Config.getBucket()).withPrefix(prefix))
          .getObjectSummaries()
          .stream()
          .map(obj -> amazonS3.getUrl(s3Config.getBucket(), obj.getKey()).toString())
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("S3 파일 목록 조회 중 오류 발생", e);
      throw new CustomException(S3ErrorCode.FILE_SERVER_ERROR);
    }
  }

  private void existFile(String keyName) {
    if (!amazonS3.doesObjectExist(s3Config.getBucket(), keyName)) {
      throw new CustomException(S3ErrorCode.FILE_NOT_FOUND);
    }
  }

  private void validateFile(MultipartFile file) {
    if (file.getSize() > 5 * 1024 * 1024) {
      throw new CustomException(S3ErrorCode.FILE_SIZE_INVALID);
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      throw new CustomException(S3ErrorCode.FILE_TYPE_INVALID);
    }
  }

  public void deleteFileByUrl(String fileUrl) {
    String bucketUrlPrefix =
        "https://" + s3Config.getBucket() + ".s3." + s3Config.getRegion() + ".amazonaws.com/";

    if (!fileUrl.startsWith(bucketUrlPrefix)) {
      throw new CustomException(S3ErrorCode.FILE_NOT_FOUND); // 또는 다른 오류 정의
    }

    // URL에서 keyName만 추출
    String keyName = fileUrl.substring(bucketUrlPrefix.length());

    deleteFile(keyName);
  }
}
