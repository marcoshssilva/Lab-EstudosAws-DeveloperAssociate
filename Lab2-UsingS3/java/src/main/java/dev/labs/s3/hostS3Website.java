package dev.labs.s3;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import com.amazonaws.services.s3.model.GetBucketWebsiteConfigurationRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class hostS3Website {

    static getInputs config = new getInputs();

    public static void main(String[] args) throws Exception {

        // Read inputs - bucket name, lab region
        

        // Create S3 service client
        

        // Configure website setting for your bucket
        

        //Close S3 service client

    }

    private static void setWebsiteConfig(S3Client s3, String bucketName, String indexPage, String errorPage) throws Exception{
        try {

            // Build website configuration using Index and Error pages
            

            // Build request using bucket name and website configuration
            

            // Run request to apply website configurations to your bucket
            

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}