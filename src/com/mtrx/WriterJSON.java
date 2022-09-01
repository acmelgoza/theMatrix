package com.mtrx;

/**
 * Writes a list of objects to a json file
 * @author amelgoza and ppande
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriterJSON {
	public static void jsonWriter(String file, List<?> list) {
		// Creates new organized Gson object
		Gson itemGson = new GsonBuilder().setPrettyPrinting().create();
		// Writes list of objects in json to file provided
		try (FileWriter writer = new FileWriter(file)) {
			itemGson.toJson(list, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
