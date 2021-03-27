let urlParams = {};

//process the url parameters and display messages
(window.onpopstate = function () {
    let match,
        pl = /\+/g,  // Regex for replacing addition symbol with a space
        search = /([^&=]+)=?([^&]*)/g,
        decode = function (s) {
            return decodeURIComponent(s.replace(pl, " "));
        },
        query = window.location.search.substring(1);

	//split the parameters into individual elemets in an array
    while (match = search.exec(query)) {
        if (decode(match[1]) in urlParams) {
            if (!Array.isArray(urlParams[decode(match[1])])) {
                urlParams[decode(match[1])] = [urlParams[decode(match[1])]];
            }
            urlParams[decode(match[1])].push(decode(match[2]));
        } else {
            urlParams[decode(match[1])] = decode(match[2]);
        }
    }
    
    //check for ?error. if exists show the error divider indicating incorrect username or password
    if(!("error" in urlParams))
    {
    	document.getElementById("error").style.display = "none"; 
    }
    
    //check for ?logout. if exists display logout message to user
    if(!("logout" in urlParams))
    {
    	document.getElementById("logout").style.display = "none"; 
    }
    
})();