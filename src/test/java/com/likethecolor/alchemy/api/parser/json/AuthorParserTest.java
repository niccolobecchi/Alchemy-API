/**
 * File: AuthorParserTest.java
 *
 * Copyright 2012 Dan Brown <dan@likethecolor.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.likethecolor.alchemy.api.parser.json;

import com.likethecolor.alchemy.api.entity.AuthorAlchemyEntity;
import com.likethecolor.alchemy.api.entity.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorParserTest {
  private static final String LANGUAGE = "english";
  private static final String STATUS_STRING = "OK";
  private static final String TEXT = "this is some text";
  private static final String USAGE = "By accessing AlchemyAPI or using information generated by AlchemyAPI, you are agreeing to be bound by the AlchemyAPI Terms of Use: http://www.alchemyapi.com/company/terms.html";
  private static final String URL = "http://www.bartleby.com/124/pres32.html";
  private static final String AUTHOR = "Dan Brown";

  @Test
  public void testParse() {
    final AuthorParser parser = new AuthorParser();
    final Response<AuthorAlchemyEntity> response = parser.parse(getJsonString());

    assertEquals(1, response.size());

    AuthorAlchemyEntity entity = response.iterator().next();

    assertEquals(AUTHOR, entity.getAuthor());
  }

  @Test
  public void testParse_NoAuthor() {
    final AuthorParser parser = new AuthorParser();
    final Response<AuthorAlchemyEntity> response = parser.parse(getJsonString_NoAuthor());

    assertEquals(0, response.size());
  }

  @Test
  public void testParse_NullEmptyJsonString() {
    final AuthorParser parser = new AuthorParser();
    Response<AuthorAlchemyEntity> response = parser.parse(null);

    assertEquals(0, response.size());

    response = parser.parse("");

    assertEquals(0, response.size());

    response = parser.parse("\t  \r\n");

    assertEquals(0, response.size());
  }

  private String getJsonString() {
    return "{" +
           "\"" + JSONConstants.RESULTS_STATUS + "\":\"" + STATUS_STRING + "\"," +
           "\"" + JSONConstants.RESULTS_USAGE + "\":\"" + USAGE + "\"," +
           "\"" + JSONConstants.RESULTS_URL + "\":\"" + URL + "\"," +
           "\"" + JSONConstants.RESULTS_LANGUAGE + "\":\"" + LANGUAGE + "\"," +
           "\"" + JSONConstants.RESULTS_TEXT + "\":\"" + TEXT + "\"," +
           "\"" + JSONConstants.AUTHOR_KEY + "\":\"" + AUTHOR + "\"" +
           "}";
  }

  /**
   * JSON with no author
   */
  private String getJsonString_NoAuthor() {
    return "{" +
           "\"" + JSONConstants.RESULTS_STATUS + "\":\"" + STATUS_STRING + "\"," +
           "\"" + JSONConstants.RESULTS_USAGE + "\":\"" + USAGE + "\"," +
           "\"" + JSONConstants.RESULTS_URL + "\":\"" + URL + "\"," +
           "\"" + JSONConstants.RESULTS_LANGUAGE + "\":\"" + LANGUAGE + "\"," +
           "\"" + JSONConstants.RESULTS_TEXT + "\":\"" + TEXT + "\"" +
           "}";
  }
}
