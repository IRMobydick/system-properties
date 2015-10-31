/*
 * Copyright (C) 2015. Jared Rummler <me@jaredrummler.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jared on 10/30/2015.
 */
public class SystemPropertiesParser {

  public static List<SystemProperty> parse() throws IOException {
    URL xls = SystemPropertiesParser.class.getResource("SystemProperties.xls");
    FileInputStream fis = new FileInputStream(new File(xls.getPath()));
    HSSFWorkbook workbook = new HSSFWorkbook(fis);
    HSSFSheet sheet = workbook.getSheetAt(0);
    int numRows = sheet.getLastRowNum();
    List<SystemProperty> systemProperties = new ArrayList<>(numRows);
    for (int i = 1; i < numRows; i++) {
      HSSFRow row = sheet.getRow(i);
      SystemProperty.Builder builder = SystemProperty.newSystemProperty();
      builder.key(row.getCell(0).getStringCellValue());
      HSSFCell cell = row.getCell(1);
      if (cell != null) {
        builder.desc(cell.getStringCellValue());
      }
      List<SystemProperty.GithubInfo> github = new ArrayList<>();
      String uses = row.getCell(2).getStringCellValue();
      String[] arr = uses.split(" ");
      for (String str : arr) {
        String repo = str.split("/")[0];
        String path = str.substring(repo.length() + 1);
        SystemProperty.GithubInfo githubInfo = new SystemProperty.GithubInfo(repo, path);
        github.add(githubInfo);
      }
      builder.github(github);
      systemProperties.add(builder.build());
    }
    fis.close();
    Collections.sort(systemProperties, (o1, o2) -> o1.key.compareToIgnoreCase(o2.key));
    return systemProperties;
  }
}
