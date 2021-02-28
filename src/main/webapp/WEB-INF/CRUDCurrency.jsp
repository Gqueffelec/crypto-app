<section class="h-100">
    <div class="container-fluid h-100">
        <div class="row align-items-center justify-content-around h-25">
            <div class="col-xs align-self-center ">
                <button type="button" class="btn btn-info custom" data-toggle="modal" data-target="#add">
                    Add a new CryptoCurrency</button>
            </div>
            <div class="col-xs align-self-center">
                <button type="button" class="btn btn-info custom" data-toggle="modal" data-target="#rmv">
                    Remove a CryptoCurrency</button>
            </div>
            <div class="col-xs align-self-center">
                <button type="button" class="btn btn-info custom" data-toggle="modal" data-target="#upd"
                    id="updatebutton">
                    Update a CryptoCurrency</button>
            </div>
            <div class="col-xs align-self-center">
                <button type="button" class="btn btn-info custom" id="list">
                    List all the CryptoCurrency</button>
            </div>
        </div>

        <div class="container" id="showlist">
            <div class="col-xs-1-1">
                <h4>Id</h4>
                <c:forEach items="${list}" var="crypto">
                    <p value="${ crypto.id}">${ crypto.id}</p>
                </c:forEach>
            </div>
            <div class="col-xs-3-3">
                <h4>Name</h4>
                <c:forEach items="${list}" var="crypto">
                    <p value="${ crypto.id}">${ crypto.name}</p>
                </c:forEach>
            </div>
            <div class="col-xs-5-5">
                <h4>Label</h4>
                <c:forEach items="${list}" var="crypto">
                    <p value="${ crypto.id}">${ crypto.label}</p>
                </c:forEach>
            </div>
            <div class="col-xs-7-7">
                <h4>Actual Price</h4>
                <c:forEach items="${list}" var="crypto">
                    <p value="${ crypto.id}">${ crypto.actualPrice}</p>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<!-- Modal -->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add a new CryptoCurrency</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label for="name">Name</label>
                <input type="text" name="name" class="name">
                <label for="label">Label</label>
                <input type="text" name="label" class="label">
                <label for="actualprice">Actual Price</label>
                <input type="number" name="actualprice" class="actualprice">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="create">Create</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="rmv" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Remove a CryptoCurrency</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label for="id">Id</label>
                <input type="number" name="id" class="id">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="remove">Remove</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="upd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Update a CryptoCurrency</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="idselector">
                    <label for="id">Name</label>
                    <select name="id" class="id">
                        <c:forEach items="${list}" var="crypto">
                            <option value="${ crypto.id}">${ crypto.name}</option>
                        </c:forEach>
                    </select>
                    <button type="button" class="btn btn-primary" id="selectId">Select</button>
                </div>
                <div class="col-xs-1-12" id="updatedata">
                    <label for="name">Name</label>
                    <input type="text" name="name" class="name">
                    <label for="label">Label</label>
                    <input type="text" name="label" class="label">
                    <label for="actualprice">Actual Price</label>
                    <input type="number" name="actualprice" class="actualprice">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="update" disabled>Update</button>
            </div>
        </div>
    </div>
</div>