const uploadToServer = async (event) => {

    event.preventDefault();

    const image = document.getElementById("image");

    const data = { "image": image };


    fetch("http://localhost:4000/", {
        method: "POST",
        headers: {
            'Content-Type': 'multi-part/form-data',
        },
        body: JSON.stringify(data)


    }).then(
        (res)=>{
            console.log(res);

        }
    ).catch(
        (err)=>{
            alert(err)
        }
    )
}