# Wamoli

## 权限关系

 * role_limits是用来设置菜单与功能按钮
 * user_limits是用户直接关联菜单及功能按钮
 * 只要有菜单权限，那么就有模块权限
 * user_roles 用户有哪个角色，那么就有对应权限
 * data_ limits 数据权限，未实现

## extjs 生成
  同时生成pc端与app端
  
    sencha -sdk  D:\tools\extjs6.2  generate  app -s D:\tools\extjs6.2\templates\admin-dashboard   distribution F:\git\app
  
  生成多个项目，生成Workspace
  
    sencha -sdk D:\tools\extjs6.2 generate workspace f:\git\app
    sencha generate app -ext myappone  ./myappone
    cd myappone 
    sencha generate view myview
    
    sencha app build production #构建为生产环境项目 
    sencha app build testing #构建为测试环境项目
    sencha app build package #创建一个独立的，可再分配的工程环境
    sencha app build native #把我们的程序打包成本地应用程序
    
This application was generated using JHipster 3.12.2, you can find documentation and help at [https://jhipster.github.io/documentation-archive/v3.12.2](https://jhipster.github.io/documentation-archive/v3.12.2).

## Development

To start your application in the dev profile, simply run:

    ./mvnw


For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

To optimize the Wamoli application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war


Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test
### Other tests

Performance tests are run by [Gatling][] and written in Scala. They're located in `src/test/gatling` and can be run with:

    ./mvnw gatling:execute

For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the `src/main/docker` folder to launch required third party services.
For example, to start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/mysql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mysql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod docker:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`yo jhipster:docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To set up a CI environment, consult the [Setting up Continuous Integration][] page.

[JHipster Homepage and latest documentation]: https://jhipster.github.io
[JHipster 3.12.2 archive]: https://jhipster.github.io/documentation-archive/v3.12.2

[Using JHipster in development]: https://jhipster.github.io/documentation-archive/v3.12.2/development/
[Using Docker and Docker-Compose]: https://jhipster.github.io/documentation-archive/v3.12.2/docker-compose
[Using JHipster in production]: https://jhipster.github.io/documentation-archive/v3.12.2/production/
[Running tests page]: https://jhipster.github.io/documentation-archive/v3.12.2/running-tests/
[Setting up Continuous Integration]: https://jhipster.github.io/documentation-archive/v3.12.2/setting-up-ci/

[Gatling]: http://gatling.io/
