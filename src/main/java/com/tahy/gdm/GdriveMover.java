package com.tahy.gdm;

import java.io.IOException;

import com.google.api.gax.paging.Page;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GdriveMover implements HttpFunction {

    @Override
    public void service(HttpRequest request, HttpResponse response)
            throws IOException {
        // 1.validation
        // POST {"file_path": "the file's path want to move", "target_file_path":"target
        // path."}
        JsonObject reqJson = new Gson().fromJson(new String(request.getInputStream().readAllBytes()), JsonObject.class);
        if (reqJson.get("source_file_path").isJsonNull() || reqJson.get("target_file_path").isJsonNull()) {
            response.setStatusCode(400);
            response.getWriter().write("source_file_path and target_file_path must be provided.");
            return;
        }

        String sourceFilePath = reqJson.get("source_file_path").getAsString();
        String targetFilePath = reqJson.get("target_file_path").getAsString();
        System.out.println("source_file_path:" + sourceFilePath + " target_file_path:" + targetFilePath);

        // check is exist requested file
        Storage storage = StorageOptions.getDefaultInstance().getService();

        System.out.println("Buckets:");
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }

        // move to Google Drive
    }
}