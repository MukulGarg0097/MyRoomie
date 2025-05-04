package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.FileEntity;

@Repository
public interface IFileRepository extends JpaRepository<FileEntity,Integer>{

}