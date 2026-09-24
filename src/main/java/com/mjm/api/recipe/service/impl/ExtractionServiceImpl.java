package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import com.mjm.api.recipe.dto.Extraction.RecipeExtraction;
import com.mjm.api.recipe.dto.Extraction.RecipeUrlRequest;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.service.ExtractionService;

@Service 
public class ExtractionServiceImpl implements  ExtractionService{

    private final RestClient restClient;
    // private final ByteArrayResource byteArrayResource;

    public ExtractionServiceImpl(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://127.0.0.1:9000")
                .build();
    }
    
    @Override
    public RecipeExtraction extractRecipeFromUrl(RecipeUrlRequest url) {
        return restClient.post()
                .uri("/extract/url")
                .body(url)
                .retrieve()
                .body(RecipeExtraction.class);
        // String response = restClient.post()
        //         .uri("/extract/url")
        //         .body(url)
        //         .retrieve()
        //         .body(String.class);

        // System.out.println("PYTHON RESPONSE:");
        // System.out.println(response);

        // return response;
    }

//     @Override
// public RecipeExtraction extractRecipeFromUrl(String url) {

//     RecipeUrlRequest request = new RecipeUrlRequest(url);

//     return restClient.post()
//             .uri("/extract/url")
//             .body(request)
//             .retrieve()
//             .body(RecipeExtraction.class);
// }

    @Override
    public RecipeExtraction extractRecipeFromImages(List<MultipartFile> images) {
        // MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

        // for (MultipartFile image : images) {
        //     bodyBuilder.part(
        //             "images",
        //             new MultipartFileResource(image)
        //     );
        // }

        // MultiValueMap<String, HttpEntity<?>> multipartData =
        //         bodyBuilder.build();

        // return restClient.post()
        //         .uri("/extract/image")
        //         .contentType(MediaType.MULTIPART_FORM_DATA)
        //         .body(multipartData)
        //         .retrieve()
        //         .body(ExtractionResponse.class);
        return null;
    }

}






//     public ExtractionResponse extract(List<MultipartFile> images) {

//         MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

//         for (MultipartFile image : images) {
//             bodyBuilder.part(
//                     "images",
//                     new MultipartFileResource(image)
//             );
//         }

//         MultiValueMap<String, HttpEntity<?>> multipartData =
//                 bodyBuilder.build();

//         return restClient.post()
//                 .uri("/extract")
//                 .contentType(MediaType.MULTIPART_FORM_DATA)
//                 .body(multipartData)
//                 .retrieve()
//                 .body(ExtractionResponse.class);
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