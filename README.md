# README

## Programming

### Write DDDML Model File

In the `dddml` directory in the root of the repository, create a DDDML file: [townesquare.yaml](./dddml/townesquare.yaml).


### Run dddappp Project Creation Tool

#### Update dddappp Docker Image

Since the dddappp v0.0.1 image is updated frequently, you may be required to manually delete the image and pull it again before `docker run`.

```shell
# If you have already run it, you may need to Clean Up Exited Docker Containers first
docker rm $(docker ps -aq --filter "ancestor=wubuku/dddappp-aptos:0.0.1")
# remove the image
docker image rm wubuku/dddappp-aptos:0.0.1
# pull the image
git pull wubuku/dddappp-aptos:0.0.1
```

---

In repository root directory, run:

```shell
docker run \
-v .:/myapp \
wubuku/dddappp-aptos:0.0.1 \
--dddmlDirectoryPath /myapp/dddml \
--boundedContextName Townesquare.SC \
--aptosMoveProjectDirectoryPath /myapp/aptos-contracts \
--boundedContextAptosPackageName TownesquareSC \
--boundedContextAptosNamedAddress townesquare_sc \
--boundedContextJavaPackageName xyz.townesquare.sc \
--javaProjectsDirectoryPath /myapp/aptos-java-service \
--javaProjectNamePrefix townesquaresc \
--pomGroupId xyz.townesquaresc
```


The command parameters above are straightforward:

* The first line indicates mounting your local directory into the `/myapp` directory inside the container.
* `dddmlDirectoryPath` is the directory where DDDML model files are located. It should be a readable directory path in the container.
* Interpret the value of parameter `boundedContextName` as the name of your application you want to develop. When there are multiple parts in your name, separate them with dots and use PascalCase naming style for each part. Bounded-context is a term in Domain-driven design (DDD) that refers to a specific problem domain scope that contains specific business boundaries, constraints, and language. If you don't understand this concept for now, it's not a big deal.
* `aptosMoveProjectDirectoryPath` is directory path where on-chain Aptos contract code is placed. It should be a readable and writable directory path in container.
* `boundedContextAptosPackageName` is package name of on-chain Aptos contracts. It's recommended to use PascalCase naming style.
* `boundedContextAptosNamedAddress` is default named address of on-chain Aptos contracts. It's recommended to use snake_case naming style.
* `boundedContextJavaPackageName` is Java package name of off-chain service. According to Java naming conventions, it should be all lowercase and parts should be separated by dots.
* `javaProjectsDirectoryPath` is directory path where off-chain service code is placed. Off-chain service consists of multiple modules (projects). It should be a readable and writable directory path in container.
* `javaProjectNamePrefix` is name prefix of each module of off-chain service. It's recommended to use an all-lowercase name.
* `pomGroupId` is GroupId of off-chain service. We use Maven as project management tool for off-chain service. It should be all lowercase and parts should be separated by dots.

After executing above command successfully, two directories `aptos-contracts` and `aptos-java-service` should be added to local current directory.


### Implementing Business Logic

If CRUD is all the business logic you need, You **don't** need to write a single line of code other than the DDDML model above. You **can** just start testing your application.


## Test the Application

### Some preparatory work that may need to be done

It should be noted that below we assume that you will publish the Move contract to the Aptos devnet, so we skip the explanation of the modifications to some configuration files required for publishing to other networks.

We can create a new account on devnet to perform the following test.

Confirm that Aptos CLI is installed and enter the directory `aptos-contracts`, then run:

```shell
cd aptos-contracts
aptos init
# Press Enter to confirm using the default values:
aptos account fund-with-faucet --account default --amount 50000000000
# View Aptos Profiles:
aptos config show-profiles
```

### Compile Aptos Move contracts

In the directory `aptos-contracts`, execute the compilation, which should now succeed:

```shell
aptos move compile --named-addresses townesquare_sc=default
```

At this point, the coding phase of the application development is complete! Isn't it very simple?


### Publish the Aptos contracts

Execute the following command in the directory `aptos-contracts` to publish the contracts to the chain:

```shell
aptos move publish --named-addresses townesquare_sc=default --assume-yes
```


### Tip: Using this Cheatsheet

Here it is a cheatsheet on how to use the Aptos Client CLI to call on-chain contracts: [AptosMoveCLICheatsheet](./aptos-contracts/AptosMoveCLICheatsheet.md)

The parameters you need to fill in are placeholders containing their type and meaning (name). You can copy these commands, modify them as needed, and execute them directly in a terminal.

### Initialize the On-chain Contracts

We will use Aptos CLI and other command line tools (`curl`, `jq`) to test the published contracts below.

Use `aptos move run` command to submit a transaction and initialize the contract:

```shell
aptos move run --function-id 'default::townesquare_sc_init::initialize' --assume-yes
```

Initialize the TownesquareState singleton object (the last two parameters are the addresses of the User Admin and Post Admin, respectively):

```shell
aptos move run --function-id 'default::townesquare_state_aggregate::create' \
--args bool:false address:0xd19e5d4634d89efe118138177628d8b2137918bb634fe461ce6061c99a56a0be address:0xd19e5d4634d89efe118138177628d8b2137918bb634fe461ce6061c99a56a0be \
--assume-yes
```

### CRUD Posts


#### Create Posts

Create a post:

```shell
aptos move run --function-id 'default::post_aggregate::create' \
--args address:0xd19e5d4634d89efe118138177628d8b2137918bb634fe461ce6061c99a56a0be 'string:test_user_id' 'string:test_post_content' 'string:post_digest_xxxx' \
--assume-yes
```

> TODO
>
> More test examples ...



## First Improvement

Looking at the generated code, you might want to merge the three event types for `Post` operations into one. This would reduce the amount of code by quite a bit.

We can do that.

Modify the model file by adding these lines:

```yaml
    properties:
      # ...
      Digest: 
        type: String
        length: 66
    # Add the following lines of code
    methods:
      Create:
        event:
          type: PostEvent
          discriminatorValue: 0
      Update:
        event:
          type: PostEvent
          discriminatorValue: 1
      Delete:
        event:
          type: PostEvent
          discriminatorValue: 2
    eventObjects:
      PostEvent:
        discriminator: EventType
        properties:
          EventType:
            type: u8

```

Remove all Move code in the `sources` directory and re-execute the dddappp tool to generate that. You'll get an application that is no different in features, but more simplified.

## Second Improvement

The original contracts don't have "Update Post" and "Delete User" operations, and you probably want the Low-Code version to be the same.

Of course, we can tell the preprocessor not to generate these methods.

Follow the instructions in the comments below to modify the model file:

```yaml
aggregates:
  Post:
    metadata:
      Preprocessors: [ "MOVE_CRUD_IT" ]
      # Add the following line
      CRUD_IT_NO_UPDATE: true
    # ...
    methods:
      # ...
      # Comment out or remove the following 4 lines
      # Update:
      #   event:
      #     type: PostEvent
      #     discriminatorValue: 1

  # ...
  User:
    metadata:
      Preprocessors: [ "MOVE_CRUD_IT" ]
      # Add the following line of code
      CRUD_IT_NO_DELETE: true
```

Remove all Move code in the `sources` directory and re-execute the dddappp tool to generate that.

> **Hint**
>
> Since the dddappp v0.0.1 docker image is updated frequently, you may be required to manually delete the image and pull it again before `docker run`.


## Modifying Business Logic

The tool has generated some files with the suffix `_logic.move` in the directory `aptos-contracts/sources`. 

Generally, these files contain the scaffolding code of functions that implement business logic, namely the signature part of the functions. You just need to fill in the implementation part of the functions.

Above, the `MOVE_CRUD_IT` preprocessor already generates the full CRUD methods for us. But it is possible that you feel that the default generated CRUD logic does not meet your needs.

For example, in our example here, you will most likely need to add some permission control logic to the user's operations.

### Only Certain Account Can Modify Global Config

Open the file `townesquare_state_create_logic.move` and add a line of code at the beginning of the `verify` method:

```move
    public(friend) fun verify(
        // ...
    ): townesquare_state::TownesquareStateCreated {
        // Add the following line of code
        townesquare_sc::genesis_account::assert_genesis_account(account);
        // ...
    }
```

Open the `townesquare_state_update_logic.move` and `townesquare_state_delete_logic.move` files, respectively. Make the same modifications.

### Modify the Logic of Creating Post

Open the file `post_create_logic.move`, add 5 lines of code as noted in the comments below:

```move
    friend townesquare_sc::post_aggregate;

    // Add the following 3 lines
    use  townesquare_sc::townesquare_state;
    const EIS_EMERGENCY: u64 = 117;
    const EINVALID_ACCOUNT: u64 = 118;

    public(friend) fun verify(
        // ...
    ): post::PostEvent {
        // Add the following 2 lines
        assert!(!townesquare_state::singleton_is_emergency(), EIS_EMERGENCY);
        assert!(poster == std::signer::address_of(account), EINVALID_ACCOUNT);
        // ...
    }
```

### Modify the Logic of Deleting Post

Open the file `post_delete_logic.move`, add 5 lines of code as noted in the comments below:

```move
    friend townesquare_sc::post_aggregate;

    // Add the following 3 lines
    use townesquare_sc::townesquare_state;
    const EIS_EMERGENCY: u64 = 117;
    const EINVALID_ACCOUNT: u64 = 118;

    public(friend) fun verify(
        // ...
    ): post::PostEvent {
        // Add the following 2 lines
        assert!(!townesquare_state::singleton_is_emergency(), EIS_EMERGENCY);
        assert!(townesquare_state::singleton_post_admin() == std::signer::address_of(account) || post::poster(post) == std::signer::address_of(account), EINVALID_ACCOUNT);
        // ...
    }
```

### Modify the Logic of Creating User

Open the file `user_create_logic.move`, add 5 lines of code as noted in the comments below:

```move
    friend townesquare_sc::user_aggregate;

    // Add the following 3 lines
    use  townesquare_sc::townesquare_state;
    const EIS_EMERGENCY: u64 = 117;
    const EINVALID_ACCOUNT: u64 = 118;

    public(friend) fun verify(
        // ...
    ): user::UserCreated {
        // Add the following 2 lines
        assert!(!townesquare_state::singleton_is_emergency(), EIS_EMERGENCY);
        assert!(user_wallet == std::signer::address_of(account), EINVALID_ACCOUNT);
        // ...
    }
```

### Modify the Logic of Updating User

Open the file `user_update_logic.move`, add 5 lines of code as noted in the comments below:

```move
    friend townesquare_sc::user_aggregate;

    // Add the following 3 lines
    use  townesquare_sc::townesquare_state;
    const EIS_EMERGENCY: u64 = 117;
    const EINVALID_ACCOUNT: u64 = 118;

    public(friend) fun verify(
        // ...
    ): user::UserUpdated {
        // Add the following 2 lines
        assert!(!townesquare_state::singleton_is_emergency(), EIS_EMERGENCY);
        assert!(user::user_wallet(user) == std::signer::address_of(account), EINVALID_ACCOUNT);
        // ...
    }
```

---

Above, we have added a total of 23 lines of code. Now we've got a feature-equivalent to the original version.

## Test Off-chain Service

After running the latest version of the dddappp tool, an Off-chain service project will be generated in the aptos directory. 
It can pull application events and entity states on the chain into the off-chain database, and provides query APIs.

### Prerequisites

The off-chain services generated by the tool are written in Java and use the MySQL database by default.

So before getting started, you need to set up a basic Java development environment:

* Install JDK and [Maven](https://maven.apache.org).
* Install MySQL database server.

If you have already installed Docker, you can use Docker to run a MySQL database service. For example:

```shell
sudo docker run -p 3306:3306 --name mysql \
-v ~/docker/mysql/conf:/etc/mysql \
-v ~/docker/mysql/logs:/var/log/mysql \
-v ~/docker/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7
```

### Creating and Initialize Database for Off-Chain Service

Use a MySQL client to connect to the local MySQL server and execute the following script to create an empty database (assuming the name is `test2`):

```sql
CREATE SCHEMA `test2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```

Go to the `sui-java-service` directory and package the Java project:

```shell
mvn package
```

Then, run a command-line tool to initialize the database:

```shell
java -jar ./townesquaresc-service-cli/target/townesquaresc-service-cli-0.0.1-SNAPSHOT.jar ddl -d "./scripts" -c "jdbc:mysql://127.0.0.1:3306/test2?enabledTLSProtocols=TLSv1.2&characterEncoding=utf8&serverTimezone=GMT%2b0&useLegacyDatetimeCode=false" -u root -p 123456
```


### Configuring Off-Chain Service

Open the `application-test.yml` file located in the directory `sui-java-service/townesquaresc-service-rest/src/main/resources` and set the published contract address. 
After setting, it should look like this:

```yaml
aptos:
  contract:
    address:
      "0xd19e5d4634d89efe118138177628d8b2137918bb634fe461ce6061c99a56a0be"
    node-api:
      base-url: "https://fullnode.devnet.aptoslabs.com/v1"
```

### Starting Off-Chain Service

In the `sui-java-service` directory, run the following command to start the off-chain service:

```shell
mvn -pl townesquaresc-service-rest -am spring-boot:run
```

### Query Off-chain Service APIs

You can use the following command to query posts:

```shell
curl http://localhost:1023/api/Posts
```

Use the following command to query a post (replace `{POST_ID}` with the actual post ID):

```shell
curl http://localhost:1023/api/Posts/{POST_ID}
```

Use the following command to query the Townesquare State:

```shell
curl http://localhost:1023/api/TownesquareStates
```


## References

The DDDML model-driven Low-Code development approach is very powerful, and you can learn more about it at these links.

* [Developing a Blog Example on Aptos](https://github.com/dddappp/aptos-blog-example). It only requires 30 or so lines of code (all of which is a description of the domain model) to be written by the developer, and then generates a blog example that emulates [RoR Getting Started](https://guides.rubyonrails.org/getting_started.html) in one click, without requiring the developer to write a single line of other code.
* [Developing a Blog Example on Rooch](https://github.com/rooch-network/rooch/blob/main/examples/blog/README.md). A Rooch version of blog sample.
* [Developing a Blog Example on Sui](https://github.com/dddappp/sui-blog-example).







