[
  <#list inputList as input>
    {
      "firstName": "${input.first_name}",
      "lastName": "${input.last_name}",
      "age": ${input.age}
    }<#sep>, </#sep>
  </#list>
]
