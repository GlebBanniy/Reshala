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



    <a href="/tasks">
        <h4>Last added tasks</h4>
    </a>
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
                    <a href="/tasks/${message.id}">
                        <STRONG>${message.name!""}</STRONG>
                    </a>
                    <#if profile??>
                        <#if message.findSolver(profile)>
                            (decided)
                        </#if>
                    </#if>
                </div>
                <div class="card-footer text-muted">
                    Author: ${(message.author.username)!"<none>"}
                    <br>
                    Date: ${(message.date)?string('dd.MM.yyyy HH:mm:ss')}
                    <br>
                    <i>Tags: ${message.tag!""}</i>
                    <br />
                    <i>Rating: ${message.getResultRating()}</i>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>

    <a href="/tasks?sort=rating">
        <h4>Rating</h4>
    </a>
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
                    <a href="/tasks/${message.id}">
                        <STRONG>${message.name!""}</STRONG>
                    </a>
                    <#if profile??>
                        <#if message.findSolver(profile)>
                            (decided)
                        </#if>
                    </#if>
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