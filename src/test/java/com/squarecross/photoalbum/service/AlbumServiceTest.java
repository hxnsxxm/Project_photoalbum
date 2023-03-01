package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.domain.Photo;
import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.repository.AlbumRepository;
import com.squarecross.photoalbum.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class AlbumServiceTest {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    AlbumService albumService;

    @Test
    void getAlbum() {
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        AlbumDto resAlbum = albumService.getAlbum(savedAlbum.getAlbumId());
        assertEquals("테스트", resAlbum.getAlbumName());
    }
/*
    @Test
    void getAlbumByName() {
        Album album = new Album();
        album.setAlbumName("앨범명");
        Album savedAlbum = albumRepository.save(album);

        AlbumDto resAlbum = albumService.getAlbum(savedAlbum.getAlbumName());
        assertEquals("앨범명", resAlbum.getAlbumName());

        //Album resAlbum = albumService.getAlbum("앨범");
    }
*/
    @Test
    void testPhotoCount() {
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        // 사진을 생성하고, setAlbum을 통해 앨범을 지정해준 이후, repository에 사진을 저장한다
        Photo photo1 = new Photo();
        photo1.setFileName("사진1");
        photo1.setAlbum(savedAlbum);
        photoRepository.save(photo1);

        Photo photo2 = new Photo();
        photo2.setFileName("사진2");
        photo2.setAlbum(savedAlbum);
        photoRepository.save(photo2);

        AlbumDto albumDto = albumService.getAlbum(savedAlbum.getAlbumId());
    }

    @Test
    void testAlbumCreate() throws IOException {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumName("새로운 앨범4");
        AlbumDto newAlbum = albumService.createAlbum(albumDto);

        albumService.deleteAlbumDirectory(newAlbum);
    }

    @Test
    void testAlbumRepository() throws InterruptedException {
        Album album1 = new Album();
        Album album2 = new Album();
        album1.setAlbumName("aaaa");
        album2.setAlbumName("aaab");

        albumRepository.save(album1);
        TimeUnit.SECONDS.sleep(1);
        albumRepository.save(album2);

        List<Album> resDate = albumRepository.findByAlbumNameContainingOrderByCreatedAtDesc("aaa");
        assertEquals("aaab", resDate.get(0).getAlbumName());
        assertEquals("aaaa", resDate.get(1).getAlbumName());
        assertEquals(2, resDate.size());

        List<Album> resDateAsc = albumRepository.findByAlbumNameContainingOrderByCreatedAtAsc("aaa");
        assertEquals("aaab", resDateAsc.get(1).getAlbumName());
        assertEquals("aaaa", resDateAsc.get(0).getAlbumName());
        assertEquals(2, resDateAsc.size());

        List<Album> resName = albumRepository.findByAlbumNameContainingOrderByAlbumNameAsc("aaa");
        assertEquals("aaaa", resName.get(0).getAlbumName());
        assertEquals("aaab", resName.get(1).getAlbumName());
        assertEquals(2, resName.size());

        List<Album> resNameDesc= albumRepository.findByAlbumNameContainingOrderByAlbumNameDesc("aaa");
        assertEquals("aaaa", resNameDesc.get(1).getAlbumName());
        assertEquals("aaab", resNameDesc.get(0).getAlbumName());
        assertEquals(2, resNameDesc.size());
    }

    @Test
    void testChangeAlbumName() throws IOException {
        //앨범 생성
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumName("변경전");
        AlbumDto res = albumService.createAlbum(albumDto);

        Long albumId = res.getAlbumId();   // 생성된 앨범 아이디 추출
        AlbumDto updateDto = new AlbumDto();
        updateDto.setAlbumName("변경후");
        albumService.changeName(albumId, updateDto);

        AlbumDto updatedDto = albumService.getAlbum(albumId);

        //앨범명 변경되었는지 확인
        assertEquals("변경후", updatedDto.getAlbumName());
    }

    @Test
    void testDeleteAlbum() throws IOException {
        // 앨범 생성
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumName("삭제 테스트");
        AlbumDto res = albumService.createAlbum(albumDto);

        // 생성된 앨범 아이디 추출
        Long albumId = res.getAlbumId();

        // 앨범 삭제
        albumService.deleteAlbum(albumId);

        // DB에서 제대로 삭제 되었는지 확인
        //AlbumDto album = albumService.getAlbum(albumId);

    }

    @Test
    void testDeleteAlbumNumber() throws IOException {
        long id = 1;
        albumService.deleteAlbumDirectoriesNumber(id);
    }


}