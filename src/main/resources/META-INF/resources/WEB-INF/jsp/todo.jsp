
            <%@ include file="common/header.jspf" %>
            <%@ include file="common/navigation.jspf" %>
            <div class="container">
                <h1>Enter Todo Details</h1>
                <form:form method="post" modelAttribute="todo">
                    <fields>
                        <form:label path="description">Description</form:label>
                        <form:input type ="text" path="description"/>
                        <form:errors path="description" cssClass="text-warning"/>
                    </fields>

                   <fields>
                        <form:label path="targetDate">Target Date</form:label>
                        <form:input type ="text" path="targetDate"/>
                        <form:errors path="targetDate" cssClass="text-warning"/>
                   </fields>

                    <form:input type ="hidden" path="id"/><br>
                    <form:input type ="hidden" path="done"/><br>
                    <input type="submit" class="btn btn-success">
                </form:form>
            </div>
            <%@ include file="common/footer.jspf" %>
            <script type="text/javascript">
                            $('#targetDate').datepicker({
                                format: 'yyyy-mm-dd'
                            });
                        </script>

