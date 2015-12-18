<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Setting URL vars --%>
<c:url value="/profile/" var="profilePath" />
<c:url value="/resources/images/achievements/" var="avatarPath" />
<c:url value="/api/comments/${param.objectName}/${param.objectId}" var="dataUrl" />
<c:url value="/api/comments/${param.objectName}/submit" var="submitUrl" />

<div <c:if test="${not empty param.elId}">id="${param.elId}"</c:if> 
     class="discussion-container ${param.elClass}" data-ng-controller="CommentController" data-ng-cloak="true"
     data-ng-init="init('${dataUrl}', {objectName: '${param.objectName}', objectId: ${param.objectId}})"
     <c:if test="${not empty param.ngShow}">data-ng-show="${param.ngShow}"</c:if>>
     
    <div class="loader" data-ng-show="loading"></div>
    <p class="text-center" data-ng-show="!data.length">No comments yet.</p>
    <div class="comments-container" data-ng-show="data" data-ng-perfect-scroll="true" data-watch="loading">
        <div data-ng-repeat="comment in data" class="comment-container">
            <div class="avatar">
                <a href="${profilePath}{{comment.user.userId}}">
                    <div class="wrapper" data-ng-bg-image="${avatarPath}{{comment.user.avatarName}}.png"></div>
                </a>
            </div>
            <div class="comment-wrapper">
                <span class="comment">{{comment.comment}}</span>
                <span class="user">
                    <a href="${profilePath}{{comment.user.userId}}">{{comment.user.username}}</a>
                </span>
                <span class="date">
                    {{comment.creation*1000 | date: 'MMM d, yyyy h:mm a'}}
                </span>
            </div>
        </div>
    </div>
     <form class="submit-comment tiles" data-ng-submit="submit('${submitUrl}')">
        <div class="form-input small tile-10">
            <input name="comment" placeholder="Write a comment..." type="text" value="" maxlength="140" data-ng-model="newComment.comment" required="required">
        </div>
        <div class="form-input small form-submit tile-2">
            <button class="small" type="submit">Post</button>
        </div>
    </form>
</div>