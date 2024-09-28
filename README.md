# Laboratórios de Estudos para Certificação AWS
---

## Comandos usados no AWS CLI

> How to see version from AWS CLI
```bash
aws --version
```

> How to configure your credentials and region
```bash
aws configure
```

> How to list your profiles
```bash
aws configure list-profiles
```

> Describe arn authentication from profile
```bash
aws sts get-caller-identity
aws sts get-caller-identity --profile default
```

> List S3 Buckets using s3(high level) s3api (low level)
```bash
aws s3 ls
aws s3 ls --profile default
aws s3api list-buckets
aws s3api list-buckets --profile default
```

> Filter buckets using query
```bash
aws s3api list-buckets \
    --output text \
    --query 'Buckets[?contains(Name, `deletemebucket`) == `true`] | [0].Name'
```

> Delete bucket
```bash
aws s3 rb s3://$YOUR_BUCKET_NAME_HERE
aws s3 rb s3://$YOUR_BUCKET_NAME_HERE --debug
```

> List policies with filter using query
```bash
aws iam list-policies \
    --output text \
    --query 'Policies[?PolicyName == `S3-Delete-Bucket-Policy`].Arn'
```

> Describe policy
```bash
aws iam get-policy-version --policy-arn $YOUR_POLICY_NAME_HERE --version-id v1
```

> Attach policy to role
```bash
aws iam attach-role-policy --policy-arn $policyArn --role-name notes-application-role
```

> List policies attached to role
```bash
aws iam list-attached-role-policies --role-name $YOUR_ROLE_NAME_HERE
```

> Change s3 bucket access with public access
```bash
aws s3api put-public-access-block --bucket $YOUR_BUCKET_NAME --public-access-block-configuration "BlockPublicPolicy=false,RestrictPublicBuckets=false"
```

> Copy directory files to s3 (can be make inverse)
```bash
## from host to S3
aws s3 sync ~/environment/labRepo/html/. s3://$mybucket/
## from S3 to host
aws s3 sync s3://$mybucket/ ~/environment/labRepo/html/.
```

> Set website endpoint to bucket using native AWS bucket endpoint
> <br /> 
> Endpoint url: http://$mybucketname.s3-website-$region.amazonaws.com
```bash

cat << EOT >> example/website.json
{
  "IndexDocument": {
      "Suffix": "index.html"
  },
  "ErrorDocument": {
      "Key": "error.html"
  }
}
EOT

aws s3api put-bucket-website --bucket $mybucket --website-configuration file://~/environment/labRepo/website.json

```


> Apply access policy to bucket (only Get to resource notes-bucket-54sd564asd6)
```bash
cat << EOT >> example/policy.json

{
  "Version": "2012-10-17",
  "Statement": [{
      "Effect": "Allow",
      "Principal": "*",
      "Action": ["s3:GetObject"],
      "Resource": "arn:aws:s3:::notes-bucket-54sd564asd6/*"
    }]
}

EOT

aws s3api put-bucket-policy --bucket $mybucket --policy file://~/environment/labRepo/policy.json
```
