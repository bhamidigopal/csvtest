


[
<#list inputList as input>
  {
  <#if input.first_name??>
    "firstName": "${input.first_name?json_string}",
  </#if>
  <#if input.last_name??>
    "lastName": "${input.last_name?json_string}",
  </#if>
  <#if input.age??>
    "age": ${input.age?c},
  </#if>
  <#if input.contact??>
    "contact": {
    <#if input.contact.city??>
      "city": "${input.contact.city?json_string}"
    </#if>
    <#if input.contact.street??> ,
      "street": "${input.contact.street?json_string}"
    </#if>
    }
  </#if>
  }<#if input?has_next>,</#if>
</#list>
]
