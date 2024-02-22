function showUpdate(id){
    if(id>0) {
        // Get the student data and fill in the form
        fetch('/fetchStudent/' + id)
            .then(response => response.json())
            .then(student => {
                document.getElementById('update-id').value = student.id;
                document.getElementById('update-name').value = student.name;
                document.getElementById('update-height').value = student.height;
                document.getElementById('update-weight').value = student.weight;
                document.getElementById('update-gpa').value = student.gpa;
                document.getElementById('update-hairColor').value = student.hairColor;
                document.getElementById('update-shape').value = student.shape;

                // Hide the select
                document.getElementById('add-form').style.display = 'none';
                
                // Show the update form
                document.getElementById('update-form').style.display = 'block';
    })
        .catch(error => console.error('Error:', error));
    }
}

function hideForm(){
    document.getElementById('add-form').style.display = 'none';
    document.getElementById('update-form').style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('update-button').addEventListener('click', function() {
        let id = document.querySelector('select[name="update-id-select"]').value;
        showUpdate(id);
    });

    document.getElementById('add-button').addEventListener('click', function() {
        document.getElementById('add-form').style.display = 'block';
        document.getElementById('update-form').style.display = 'none';
    });
});