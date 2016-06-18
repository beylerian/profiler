/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package opennlp.tools.formats.pan.pan16;

import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anthony on 4/20/16.
 */
public class PAN16Reader {

	private static final String dataFolder = "/datasets/author-profiling/pan16-author-profiling-training-dataset-english-2016-02-29/";


	public static ArrayList<PAN16Author> getPAN16Authors(int startAuthorIndex, int endAuthorIndex) {
		ArrayList<PAN16Author> authors = new ArrayList<PAN16Author>();

		try {
			HashMap<String, String[]> truthMap = PAN16Reader.getTruthMap();

			JAXBContext jaxbContext = JAXBContext.newInstance(PAN16Author.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File folder = new File(PAN16Reader.class.getResource(dataFolder).getPath());
			File[] listOfFiles = folder.listFiles();

			if (startAuthorIndex < 0 || endAuthorIndex >= listOfFiles.length) {
				throw new IndexOutOfBoundsException("an author index is out of bounds, should be in : 0-"
						+ (listOfFiles.length - 2) + " range");
			} else if (startAuthorIndex > endAuthorIndex) {
				throw new InvalidParameterException("the start index is larger than the end index");
			}

			for (int i = startAuthorIndex; i <= endAuthorIndex; i++) {
				File file = listOfFiles[i];
				if (file.isFile() && FilenameUtils.getExtension(file.getPath()).equals("xml")) {
					PAN16Author author = (PAN16Author) jaxbUnmarshaller.unmarshal(new File(file.getPath()));
					String truths[] = truthMap.get(FilenameUtils.getBaseName(file.getPath()));
					author.setGender(truths[0]);
					author.setAge_group(truths[1]);
					authors.add(author);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authors;
	}

	public static HashMap<String, String[]> getTruthMap() {

		HashMap<String, String[]> truthMap = new HashMap<String, String[]>();
		try {
			FileReader fr = new FileReader(PAN16Reader.class.getResource(dataFolder).getPath() + "truth.txt");
			BufferedReader in = new BufferedReader(fr);
			String line;
			while ((line = in.readLine()) != null) {
				String tokens[] = line.split(":::");
				String[] truth = new String[2];
				truth[0] = tokens[1];
				truth[1] = tokens[2];
				truthMap.put(tokens[0], truth);
			}
			return truthMap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

