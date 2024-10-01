## Commands

### Import client

```python
client = boto3.client('dynamodb')
``` 

### Creating new table

```python
response = client.create_table(
        AttributeDefinitions=[
            { 'AttributeName': tableDefinition["partitionKey"], 'AttributeType': 'S', },
            { 'AttributeName': tableDefinition["sortKey"], 'AttributeType': 'N', },
        ],
        KeySchema=[
            { 'AttributeName': tableDefinition["partitionKey"], 'KeyType': 'HASH',
            },
            { 'AttributeName': tableDefinition["sortKey"], 'KeyType': 'RANGE',
            },
        ],
        ProvisionedThroughput={
            'ReadCapacityUnits': int(tableDefinition["readCapacity"]),
            'WriteCapacityUnits': int(tableDefinition["writeCapacity"]),
        },
        TableName=tableDefinition["tableName"]
    )
```

### Awaiting create table

```python
waiter = client.get_waiter('table_exists')
waiter.wait(TableName=tableName)
``` 

### Put some data on table

```python
# open resource
resource = boto3.resource('dynamodb')
# select table
table = resource.Table(tableName)
# put data
table.put_item(
        Item={
            'UserId': note["UserId"],
            'NoteId': int(note["NoteId"]),
            'Note': note["Note"]
        }
    )
```

### Filter data using ExpressionAttribute

```python
response = client.query(
        TableName=tableName,
        KeyConditionExpression='UserId = :userId',
        ExpressionAttributeValues={
            ':userId': {"S": qUserId}
        },
        ProjectionExpression="NoteId, Note"
    )
```