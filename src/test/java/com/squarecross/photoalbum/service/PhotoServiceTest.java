package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.domain.Photo;
import com.squarecross.photoalbum.dto.PhotoDto;
import com.squarecross.photoalbum.repository.AlbumRepository;
import com.squarecross.photoalbum.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class PhotoServiceTest {

    @Autowired
    PhotoService photoService;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Test
    void getPhoto() {
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        Photo photo = new Photo();
        photo.setFileName("테스트 파일");
        photo.setAlbum(savedAlbum);
        Photo savedPhoto = photoRepository.save(photo);

        PhotoDto resPhoto = photoService.getPhoto(savedPhoto.getPhotoId());
        assertEquals("테스트 파일", resPhoto.getFileName());
    }
}