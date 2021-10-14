<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>



    <a href="/tasks">Last added tasks</a>
    <div class="card-deck">
        <#list messages?reverse as message>
            <#if message?counter = 4>
                <#break>
            </#if>
            <div class="card my-3">
                <div>
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-2">
                    <a href="/tasks/${message.id}">${message.name!""}</a>
                </div>
                <div class="card-footer text-muted">
                    Author: ${(message.author.username)!"<none>"}
                    <br>
                    Date: ${(message.date)?string('dd.MM.yyyy HH:mm:ss')}
                    <br>
                    <i>Tags: ${message.tag!""}</i>
                    <br />
                    <i>Rating: ${message.rating!""}</i>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>

    <h4>Rating</h4>
    <div class="card-deck">
        <#list messages?sort_by("rating")?reverse as message>
            <#if message?counter = 4>
                <#break>
            </#if>
            <div class="card my-3">
                <div>
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-2">
                    <STRONG>${message.name!""}</STRONG>

                </div>
                <div class="card-footer text-muted">
                    Author: ${(message.author.username)!"<none>"}
                    <br>
                    Date: ${(message.date)?string('dd.MM.yyyy HH:mm:ss')}
                    <br>
                    <i>Tags: ${message.tag!""}</i>
                    <br />
                    <i>Rating: ${message.rating!""}</i>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>