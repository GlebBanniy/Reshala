<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>

<#if (id==(thisUser.id)) || isAdmin>

<div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="inputTitle" class="form-label">Title</label>
            <input type="text" class="form-control" id="inputTitle" name="name" placeholder="Title" value="${message.name}" />
        </div>
        <div class="form-group">
            <label for="inputText" class="form-label">Task text</label>
            <input type="text" class="form-control" id="inputText" name="text" placeholder="Task text" value="${message.text}" />
        </div>
        <div class="form-group">
            <label for="inputCategory" class="form-label">Category</label>
            <input type="text" class="form-control" id="inputCategory" name="category" placeholder="Category" value="${message.category}" />
        </div>
        <div class="form-group">
            <label for="inputTag" class="form-label">Tag</label>
            <input type="text" class="form-control" id="inputTag" name="tag" placeholder="Tag" value="${message.tag}" />
        </div>
        <div class="form-group">
            <label for="inputAnswer" class="form-label">Answer</label>
            <input type="text" class="form-control" id="inputAnswer" name="answer1" placeholder="First Answer" value="${message.answer1!""}" />
        </div>
        <div class="form-group">
            <label for="inputAnswer" class="form-label">Answer</label>
            <input type="text" class="form-control" id="inputAnswer" name="answer2" placeholder="Second Answer" value="${message.answer2!""}" />
        </div>
        <div class="form-group">
            <label for="inputAnswer" class="form-label">Answer</label>
            <input type="text" class="form-control" id="inputAnswer" name="answer3" placeholder="Third Answer" value="${message.answer3!""}" />
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file" id="customFile"/>
                <label class="custom-file-label" for="customFile">Choose image</label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token!""}">
        <div class="form-group">
            <button type="submit" class="btn btn-primary ml-2">Edit</button>
        </div>
    </form>
</div>
<#else>
    <h4>you don't have access!</h4>
</#if>
</@c.page>