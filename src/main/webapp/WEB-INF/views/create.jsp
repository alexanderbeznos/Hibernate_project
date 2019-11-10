
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
    <link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Add Player</title>

    <script>
        $(document).ready(function () {
            $.ajax({
            url: './land',
            dataType: 'json',
            success: function(data, status, settings) {
                getCountries(data)
            }});
        })
    </script>

    <script>
        function getCountries (data) {
            var arr = data['countries'];
            for (i = 0; i < arr.length; i++){
                var country = arr[i];
                var opt = document.createElement('option');
                opt.innerHTML = country;
                opt.value = country;
                document.getElementById('country').appendChild(opt);
            }
        }
    </script>

    <script>
    function getClubs() {
        deleteOptions();
        var countryVal = $('#country').val();
        $.ajax({
            type: 'POST',
            url:'./clubs',
            data: countryVal,
            datatype: 'json',
            success: function (data, status, settings) {
                var arr = data['clubs'];
                for (var i = 0; i < arr.length; i++) {
                    var country = arr[i];
                    var opt = document.createElement('option');
                    opt.innerHTML = country;
                    opt.value = country;
                    document.getElementById('clubs').appendChild(opt);
                }
            }
        })
    }

    function deleteOptions() {
        var parent = document.getElementById('clubs');
        var nodes = parent.childNodes;
        for (var i = 0; i < nodes.length; i++) {
            var elem = nodes[i];
            if (elem.nodeValue != '') {
                parent.removeChild(elem);
                i--;
            }
        }

    }
    </script>


    <script>
        $(document).ready(function () {
            $.ajax({
                url: './position',
                dataType: 'json',
                success: function(data, status, settings) {
                    console.log(data)
                    getPosition(data)
                }});
        })
    </script>

    <script>
        function getPosition (data) {
            var arr = data['collection'];
            for (i = 0; i < arr.length; i++){
                var poz = arr[i];
                var opt = document.createElement('option');
                opt.innerHTML = poz['name'];
                opt.value = poz['id'];
                document.getElementById('area').appendChild(opt);
            }
        }
    </script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-5 offset-sm-3">
            <form action="${pageContext.request.contextPath}/addPlayer" method="post">
                <div class="form-group">
                    <label for="Name">Name</label>
                    <input type="text" class="form-control" id="Name" name="name" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name">
                </div>
                <div class="form-group">
                    <label for="marketValue">Market value</label>
                    <input type="text" class="form-control" id="marketValue" name="marketValue" placeholder="Enter market value">
                </div>
                <div class="form-group">
                    <label for="country">Country</label>
                    <select class="form-control" id="country" name="country" onchange="getClubs()">
                        <option value="" disabled selected> Select your option</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="clubs">Club</label>
                    <select class="form-control" id="clubs" name="club">
                        <option value="" disabled selected> Select your option</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="area">Position</label>
                    <select class="form-control" id="area" name="area">
                        <option value="" disabled selected> Select your option</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add player</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
