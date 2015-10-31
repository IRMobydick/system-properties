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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Jared on 10/30/2015.
 */
public class Main {

  private static final Gson PRETTY_GSON = new GsonBuilder()
      .disableHtmlEscaping().setPrettyPrinting().create();

  private static final Gson NORMAL_GSON = new GsonBuilder()
      .disableHtmlEscaping().create();

  public static void main(String[] args) throws IOException {
    List<SystemProperty> properties = SystemPropertiesParser.parse();
    System.out.println(NORMAL_GSON.toJson(properties));
    Files.write(Paths.get("system_properties_min.json"), NORMAL_GSON.toJson(properties).getBytes());
    Files.write(Paths.get("system_properties.json"), PRETTY_GSON.toJson(properties).getBytes());
  }

}
