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

import java.util.List;

/**
 * Created by Jared on 10/30/2015.
 */
public class SystemProperty {

  public final String key;
  public final String desc;
  public final List<GithubInfo> github;

  private SystemProperty(Builder builder) {
    this.key = builder.key;
    this.github = builder.github;
    this.desc = builder.desc;
  }

  public static Builder newSystemProperty() {
    return new Builder();
  }

  public static class GithubInfo {

    public final String repo;
    public final String path;

    public GithubInfo(String repo, String path) {
      this.repo = repo;
      this.path = path;
    }

    public String getClassName() {
      return path.substring(path.lastIndexOf("/") + 1).replaceFirst("[.][^.]+$", "");
    }
  }

  public static final class Builder {

    private String key;
    private List<GithubInfo> github;
    private String desc;

    private Builder() {
    }

    public SystemProperty build() {
      return new SystemProperty(this);
    }

    public Builder key(String key) {
      this.key = key;
      return this;
    }

    public Builder github(List<GithubInfo> github) {
      this.github = github;
      return this;
    }

    public Builder desc(String desc) {
      this.desc = desc;
      return this;
    }
  }

}
