var URL = "http://localhost:8080/api/company/";
const getCompanyById = async (id) => {
    let res = await axios.get(BASE_URL + '/' + id)
        .catch(function (error) {
            if (error.response) {
                console.log(error.response.data.code);
                console.log(error.response.data.message);
                console.log(error.response.data.details);
            } else if (error.request) {
                console.log(error.request);
            } else {
                console.log('Error', error.message);
            }
        });
    return res.data;
}
