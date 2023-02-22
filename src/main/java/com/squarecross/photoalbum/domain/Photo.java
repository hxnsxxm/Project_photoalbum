package com.squarecross.photoalbum.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "photo", schema = "photo_album", uniqueConstraints = {@UniqueConstraint(columnNames = "photo_id")})
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", unique = true, nullable = false)
    private Long photoId;

    @Column(name = "file_name", unique = true)
    private String fileName;

    @Column(name = "thumb_url", unique = true)
    private String thumbUrl;

    @Column(name = "original_url", unique = true)
    private String originalUrl;

    @Column(name = "file_size")
    private double fileSize;

    @Column(name = "uploaded_at")
    @CreationTimestamp
    private Date uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    //private Long albumId;
    private Album album;
}
