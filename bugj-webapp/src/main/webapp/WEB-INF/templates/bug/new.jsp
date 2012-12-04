<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>New Bug</h2>
<form:form>
    <fieldset>
        <table>
            <tr>
                <td><label for="synopsis" /></td>
                <td><form:input path="synopsis"/></td>
            </tr>
            <tr>
                <td/>
                <td>
                    <button>
                        <span>Submit</span>
                    </button>
                </td>
        </table>
    </fieldset>
</form:form>

