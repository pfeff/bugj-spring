<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>New Bug</h2>
<form:form action="create" commandName="bug">
    <fieldset>
        <table>
            <tr>
                <td><label for="synopsis">Synopsis</label></td>
                <td><form:input path="synopsis"/></td>
            </tr>
            <tr>
                <td/>
                <td>
                    <button>
                        <span>Submit</span>
                    </button>
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>

