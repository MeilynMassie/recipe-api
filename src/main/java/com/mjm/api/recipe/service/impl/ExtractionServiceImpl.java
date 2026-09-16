package com.mjm.api.recipe.service.impl;

import org.springframework.web.client.RestClient;

import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.service.ExtractionService;

public class ExtractionServiceImpl implements  ExtractionService{

    private final RestClient restClient;

    public ExtractionServiceImpl(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8000")
                .build();
    }
    
    @Override
    public Recipe extractRecipeFromUrl(String url) {
        return restClient.post()
                .uri("/extract/url")
                .body(new String(url))
                .retrieve()
                .body(Recipe.class);
    }
}



// @Service
// public class ImageService {

//     private final WebClient webClient;

//     public ImageService(WebClient.Builder builder) {
//         this.webClient = builder.baseUrl("http://fastapi:8000")
//                 .build();
//     }

//     public ResponseEntity<String> sendToFastApi(MultipartFile file) {

//         MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

//         bodyBuilder.part(
//                 "file",
//                 new ByteArrayResource(file.getInputStream()) {
//                     @Override
//                     public String getFilename() {
//                         return file.getOriginalFilename();
//                     }
//                 });

//         String response = webClient.post()
//                 .uri("/predict")
//                 .contentType(MediaType.MULTIPART_FORM_DATA)
//                 .body(
//                     BodyInserters.fromMultipartData(
//                         bodyBuilder.build()))
//                 .retrieve()
//                 .bodyToMono(String.class)
//                 .block();

//         return ResponseEntity.ok(response);
//     }
// }



// FAST API CODE
// from typing import List

// from fastapi import FastAPI, UploadFile, File

// app = FastAPI()

// @app.post("/analyze")
// async def analyze(
//     images: List[UploadFile] = File(...)
// ):

//     image_bytes = []

//     for image in images:
//         image_bytes.append(await image.read())

//     # Run model against all images together

//     result = {
//         "classification": "PASS",
//         "confidence": 0.97
//     }

//     return result





// MultipartBodyBuilder builder = new MultipartBodyBuilder();

// for (MultipartFile file : files) {
//     builder.part(
//         "files",
//         new ByteArrayResource(file.getBytes()) {
//             @Override
//             public String getFilename() {
//                 return file.getOriginalFilename();
//             }
//         }
//     );
// }

// String response = webClient.post()
//         .uri("/predict")
//         .contentType(MediaType.MULTIPART_FORM_DATA)
//         .body(BodyInserters.fromMultipartData(builder.build()))
//         .retrieve()
//         .bodyToMono(String.class)
//         .block();