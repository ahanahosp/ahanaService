package com.ahana.api.dao.mongo;

import java.util.List;

import com.ahana.api.domain.mongo.ImageVO;

public interface FileStorageDao {

	public String store(ImageVO imageVO);

	public ImageVO getById(String id);

	public ImageVO getByFileName(String fileName);

	public List<ImageVO> findAll();

}
