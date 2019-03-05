package com.zengtengpeng.file.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.file.service.FileImagesService;
import com.zengtengpeng.file.dao.FileImagesDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileImagesServiceImpl implements FileImagesService {

	@Resource
	private FileImagesDao fileImagesDao;


    @Override
    public FileImagesDao initDao() {
        return fileImagesDao;
    }
}
