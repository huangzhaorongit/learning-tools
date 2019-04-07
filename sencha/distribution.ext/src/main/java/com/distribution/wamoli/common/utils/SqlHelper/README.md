#Mybatis工具类

##SqlHelper_old.java支持3.1.x~3.2.x

##SqlHelper.java支持3.2.x~3.3.x

#SqlHelper - 获取sql

相关文章： http://blog.csdn.net/isea533/article/details/40044417

简单调用示例：  

```java
System.out.println(
    SqlHelper.getNamespaceSql(
            sqlSession,
            "com.github.pagehelper.mapper.CountryMapper.selectIf2ListAndOrder"));
			
System.out.println(
    SqlHelper.getMapperSql(
            sqlSession,
            "com.github.pagehelper.mapper.CountryMapper.selectIf2ListAndOrder",
            Arrays.asList(1, 2),
            Arrays.asList(3, 4),
            "id"));
```  

输出结果：  

```sql
select * from country
select * from country
     WHERE id not in
        (
          1
        ,
          2
        )
      
      
        and id not in
        (
          3
        ,
          4
        ) 
     
      order by id
```