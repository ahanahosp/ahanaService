package com.ahana.api.dao.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.ahana.api.common.Constants;
import com.ahana.api.domain.mongo.ImageVO;
import com.mongodb.gridfs.GridFSDBFile;

public class FileStorageDaoImpl implements FileStorageDao {

	@Autowired
	GridFsTemplate gridFsTemplate;

	@Override
	public String store(ImageVO imageVO) {
		return this.gridFsTemplate.store(imageVO.getData(), imageVO.getFileName(), imageVO.getContentType(), imageVO.getMetaData()).getId().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImageVO getById(String id) {
		ImageVO imageVO=null;
		GridFSDBFile gridFSDBFile=null;
		try{
			gridFSDBFile=this.gridFsTemplate.findOne(new Query(Criteria.where(Constants.MONGO_ID).is(id)));
			if(null!=gridFSDBFile){
				imageVO=new ImageVO();
				imageVO.setContentType(gridFSDBFile.getContentType());
				imageVO.setFileName(gridFSDBFile.getFilename());
				imageVO.setId((String)gridFSDBFile.getId());
				imageVO.setData(gridFSDBFile.getInputStream());
				imageVO.setMetaData((Map<String, String>)gridFSDBFile.getMetaData());
			}
		}finally{
			gridFSDBFile=null;
		}
		return imageVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImageVO getByFileName(String fileName) {
		ImageVO imageVO=null;
		GridFSDBFile gridFSDBFile=null;
		try{
			gridFSDBFile=gridFsTemplate.findOne(new Query(Criteria.where(Constants.FILE_NAME).is(fileName)));
			if(null!=gridFSDBFile){
				imageVO=new ImageVO();
				imageVO.setContentType(gridFSDBFile.getContentType());
				imageVO.setFileName(gridFSDBFile.getFilename());
				imageVO.setId((String)gridFSDBFile.getId());
				imageVO.setData(gridFSDBFile.getInputStream());
				imageVO.setMetaData((Map<String, String>)gridFSDBFile.getMetaData());
			}
		}finally{
			gridFSDBFile=null;
		}
		return imageVO;
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public List<ImageVO> findAll() {
		List<ImageVO> imageVOs=null;
		List<GridFSDBFile> gridFSDBFile=null;
		try{
			gridFSDBFile=gridFsTemplate.find(null);
			if(CollectionUtils.isNotEmpty(gridFSDBFile)){
				imageVOs=new ArrayList<ImageVO>();
				for(GridFSDBFile gridFSDBFile2:gridFSDBFile){
					ImageVO imageVO=new ImageVO();
					imageVO.setContentType(gridFSDBFile2.getContentType());
					imageVO.setFileName(gridFSDBFile2.getFilename());
					imageVO.setId((String)gridFSDBFile2.getId());
					imageVO.setData(gridFSDBFile2.getInputStream());
					imageVO.setMetaData((Map<String, String>)gridFSDBFile2.getMetaData());
					imageVOs.add(imageVO);
				}
			}
		}finally{
			gridFSDBFile=null;
		}
		return imageVOs;
	}
}