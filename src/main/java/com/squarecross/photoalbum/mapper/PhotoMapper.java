package com.squarecross.photoalbum.mapper;

import com.squarecross.photoalbum.domain.Photo;
import com.squarecross.photoalbum.dto.PhotoDto;

public class PhotoMapper {

    public static PhotoDto convertToDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setPhotoId(photo.getPhotoId());
        photoDto.setFileName(photo.getFileName());
        photoDto.setFileSize(photo.getFileSize());
        photoDto.setThumbUrl(photo.getThumbUrl());
        photoDto.setOriginalUrl(photo.getOriginalUrl());
        photoDto.setUploadedAt(photo.getUploadedAt());
        photoDto.setAlbumId(photo.getAlbum().getAlbumId());

        return photoDto;
    }


}
