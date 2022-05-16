# Json Flattener

## Key Features

### Json Flattener is a tool to flatten a Json file of simple or nested nature.

e.g. input :-

```
{
    "a": 1,
    "b": true,
    "c": {
        "d": 3,
        "e": "test"
    }
}
```

output :-

```
{
    "a": 1,
    "b": true,
    "c.d": 3,
    "c.e": "test"
}
```

#### The tool supports the following :-

1. Json Object, Numeric, Boolean, String types.
2. Nested Json Objects.

#### The tool does not support :-

1. Json Arrays.

### Instructions to use the tool :-

1. Clone the git repo with the following URL[https://github.com/arvindcv/JsonFlattener.git] [branch -- master]

2. Executing it from CLI
    
        1. Navigate to the JsonFlattener folder
        
        2. run the maven install [mvn clean install]

        3. run the mvn exec command [mvn exec:java -Dexec.mainClass=Flattener.Flatten]
           
        4. provide the absolute file path [test/resources/Input/Json1.txt]
        
        5. run the test command to exeucte the test cases [mvn test]
        
3. Sample Json files are provided in the test/resources/Input folder.
   
   <img width="248" alt="Screen Shot 2022-05-15 at 5 08 05 PM" src="https://user-images.githubusercontent.com/5441159/168500534-b92e8410-25d1-4e0d-a8ed-4005fdf51dae.png">
