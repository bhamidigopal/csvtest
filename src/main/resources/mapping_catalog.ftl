[
<#list inputList as input>
  {
  <#if input.api_name??>
    "name": "${input.api_name?json_string}",
  </#if>
  <#if input.api_deployed_date??>
    "deployedDate": "${input.api_deployed_date?json_string}",
  </#if>
  <#if input.api_version??>
    "version": "${input.api_version?json_string}",
  </#if>
  <#if input.api_audience??>
    "section": {
    <#if input.api_audience??>
      "audience": "${input.api_audience?json_string}",
    </#if>
    <#if input.api_category??>
      "category": "${input.api_category?json_string}"
    </#if>
    }
  </#if>
  }<#if input?has_next>,</#if>
</#list>
]
