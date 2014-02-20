<#assign mainDiv>
계절 선택:
<select>
	<#list seasons as season>
		<option>${season}</option>
	</#list>
</select>
</#assign>
<#include "/layout.ftl">
