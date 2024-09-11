const inputPesquisaFilme = document.getElementById("pesquisaFilme");
const optionList = document.getElementById("opcoesFilmes");
const resultadoPesquisa = document.getElementById('listaResultadoPesquisa');
const opcoesFilmes = document.getElementById('opcoesFilmes')



inputPesquisaFilme.addEventListener('input', function(){
    if (inputPesquisaFilme.value.length > 2 ) {
        pesquisaFilme(inputPesquisaFilme.value);
    }
    else{
        
    }
});

function pesquisaFilme(filme) {
    
    fetch(`http://localhost:8080/filme?filme=${filme}`)
    .then(response => response.json())
    .then(data => {
        optionList.innerHTML = '';
        data.slice(0, 10).forEach(filme => {
            const option = document.createElement('Option');
            option.textContent = filme.title
            option.id = filme.id;
            optionList.appendChild(option);            
            
        });
    });

}

inputPesquisaFilme.addEventListener('change', function() {
    const selectedValue = inputPesquisaFilme.value;
    const selectedOption = [...optionList.options].find(option => option.textContent === selectedValue);
    
    
    if (selectedOption) {
        const id = selectedOption.id;

        console.log(selectedOption)
        filmeSugestao(id);
        
    }
});



function filmeSugestao(id) {
    fetch(`http://localhost:8080/sugestao/${id}`)
    .then(response => response.json())
    .then(data => {
        listaResultadoPesquisa.innerHTML = '';
        data.forEach(filme => {
            const li = document.createElement('li');
            li.innerHTML = `<img src="https://image.tmdb.org/t/p/original${filme.poster_path}"/>
                            <h3>${filme.title}</h3>
                            <h4>Nota: ${filme.vote_average}</h4>                           
                            `
            listaResultadoPesquisa.appendChild(li);
        });
    })
}

function ajusteRecomendacao(filmes){



}