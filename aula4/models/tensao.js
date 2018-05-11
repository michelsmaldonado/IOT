/** Pós Graduação Internet das Coisas - CEFET-MG
	Disciplina: Programação para Sistemas de Computação
	Exemplo prático de RESTFul com NodeJS e MongoDB
	Modelo Temperatura
 */
 
var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var autoIncrement = require('mongoose-auto-increment'); 
var mongooseApiQuery = require('mongoose-api-query'); 

var TensaoSchema = new Schema({
    time: String,
    valor: String
});

autoIncrement.initialize(mongoose.connection);
TensaoSchema.plugin(autoIncrement.plugin, 'tensao');
TensaoSchema.plugin(mongooseApiQuery); 
module.exports = mongoose.model('tensao', TensaoSchema);

