package com.example.demo.base.service.impl;

import com.example.demo.base.dao.memberInformation.MemberInformationDao;
import com.example.demo.base.dao.memberInformation.MemberInformationDto;
import com.example.demo.base.domain.memberInformation.MemberForm;
import com.example.demo.base.service.MemberInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {

    @Autowired
    @Qualifier("MemberInformationDaoImpl")
    MemberInformationDao dao;

    @Override
    public boolean insertOne(MemberForm memberForm) {

        int rowNumber = dao.insertOne(memberForm);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public MemberInformationDto selectOne(String memberId) {

        //selectOne実行
        return dao.selectOne(memberId);
    }

    @Override
    public List<MemberInformationDto> selectMany(String memberId, String memberName) {
        return dao.selectMany(memberId, memberName);
    }

    @Override
    public boolean updateOne(MemberForm memberForm) {

        int rowNumber = dao.updateOne(memberForm);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteOne(String memberId) {

        int rowNumber = dao.deleteOne(memberId);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public void memberCsvOut() throws DataAccessException {
        //CSV出力
        dao.memberCsvOut();
    }

    @Override
    public byte[] getFile(String fileName) throws IOException {

        // ファイルシステム(デフォルト)の取得
        FileSystem fs = FileSystems.getDefault();

        // ファイル取得
        Path p = fs.getPath(fileName);

        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }
}
