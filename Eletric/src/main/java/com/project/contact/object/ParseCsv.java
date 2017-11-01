package com.project.contact.object;

import com.project.contact.dao.PhoneEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jwl on 2016/5/1.
 */
public class ParseCsv {
    public boolean generateCsv(File file, List<PhoneEntity> list) {
        boolean isSuccess = false;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    bw.append(list.get(i).toCsv()).append("\r");
                }
            }
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    public List<PhoneEntity> importCsv(File file) throws IOException {
        BufferedReader br = null;
        List<PhoneEntity> list = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String rec = null;// 一行
            String str;// 一个单元格
            list=new ArrayList<PhoneEntity>();
            // 读取一行
            while ((rec = br.readLine()) != null) {
                rec=str = rec.replaceAll("null", "");
                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
                Matcher mCells = pCells.matcher(rec);
                List<String> cells = new ArrayList<String>();// 每行记录一个list
                // 读取每个单元格
                while (mCells.find()) {
                    str = mCells.group();
                    str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    cells.add(str);
                }
                PhoneEntity phoneEntity=new PhoneEntity();
                phoneEntity=parsePhoneEntity(cells,phoneEntity);
                list.add(phoneEntity);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    private PhoneEntity parsePhoneEntity(List<String> cells,PhoneEntity phoneEntity){
        phoneEntity.setNumber(cells.get(0));
        phoneEntity.setNumber2(cells.get(1));
        phoneEntity.setName(cells.get(2));
        phoneEntity.setPinyin(cells.get(3));
        phoneEntity.setShengmu(cells.get(4));
        phoneEntity.setEmail(cells.get(5));
        phoneEntity.setQq(cells.get(6));
        phoneEntity.setWorkAddress(cells.get(7));
        phoneEntity.setHomeAddress(cells.get(8));
        phoneEntity.setBirthday(cells.get(9));
        phoneEntity.setHomePage(cells.get(10));
        phoneEntity.setPostCode(cells.get(11));
        phoneEntity.setImage(cells.get(12));
        phoneEntity.setMark(cells.get(13));
        return phoneEntity;
    }
}
