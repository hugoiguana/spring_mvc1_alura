<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>



<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

    <style type="text/css">
        body{
            padding: 0px 0px;
        }
    </style>

</head>
<body>

	<nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="<c:url value="/" />">Casa do Código</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="<c:url value="/produtos/listar" />">Lista de Produtos</a></li>
	        <li><a href="<c:url value="/produtos/form" />">Cadastro de Produtos</a></li>
	    </ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
	</nav>

	<div class="container">
	
		<img src="<c:url value="/resources/imagens/caelum.png"/>" />
			<c:url value="/produtos" var="produtosGravar"/>

		<form:form action="${produtosGravar}" method="post" commandName="produtos" enctype="multipart/form-data">
			<div class="form-group">
				<label>Título</label> 
				<form:input path="titulo" cssClass="form-control" />
				<form:errors path="titulo" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="descricao" cssClass="form-control" />
				<form:errors path="descricao" />
			</div>
	       <div class="form-group">
	            <label>Data de lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control"/>
	            <form:errors path="dataLancamento" />
	        </div>		
			<div class="form-group">
				<label>Páginas</label> 
				<form:input path="paginas" cssClass="form-control"/>
				<form:errors path="paginas" />
			</div class="form-group">
			
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label> 
				<form:input path="precos[${status.index}].valor" cssClass="form-control"/>
				<form:hidden path="precos[${status.index }].tipo" value="${tipoPreco}"/>
				</div>
			</c:forEach>
			
		    <div class="form-group">
		        <label>Sumário</label>
		        <input name="sumario" type="file" class="form-control" />
		    </div>		
	
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>
