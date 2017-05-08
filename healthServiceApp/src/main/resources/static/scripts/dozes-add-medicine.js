/**
 * Created by Administrator on 5/7/2017.
 */
var btn_add_doze    = document.getElementById( 'btn-add-doze' );
var btn_remove_doze = document.createElement( 'button' );
btn_remove_doze.type      = 'button';
btn_remove_doze.innerText = 'Изтрий';
btn_remove_doze.classList.add('btn');
btn_remove_doze.classList.add('btn-primary');

var doze_inputs     = document.getElementsByClassName( 'doze-input' );
var doze_input_tmpl = doze_inputs[ 0 ];

btn_add_doze.addEventListener( 'click', addDoze );

function addDoze () {
    var doze_input_clone      = doze_input_tmpl.cloneNode( true );
    var btn_remove_doze_clone = btn_remove_doze.cloneNode( true );

    doze_input_clone.getElementsByTagName( 'label' )[ 0 ].innerText += ' ' + ( 1 + doze_inputs.length );
    doze_input_clone.getElementsByTagName( 'input' )[ 0 ].value      = '';
    doze_input_clone.appendChild( btn_remove_doze_clone );

    btn_remove_doze_clone.addEventListener( 'click', deleteDoze.bind( null, doze_input_clone ) );

    btn_add_doze.parentNode.insertBefore( doze_input_clone, btn_add_doze );

    doze_input_clone.getElementsByTagName( 'input' )[ 0 ].focus();
}

function deleteDoze ( doze_input, event ) {
    doze_input.remove();
}
