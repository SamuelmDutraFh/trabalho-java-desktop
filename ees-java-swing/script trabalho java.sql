DROP SCHEMA IF EXISTS `sistemapedidos`;
CREATE SCHEMA `sistemapedidos` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `sistemapedidos`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador autoincremental para a tabela cliente. Chave primária.',
  `cpf` VARCHAR(15) NOT NULL COMMENT 'CPF do cliente',
  `nome` VARCHAR(30) NOT NULL COMMENT 'Primeiro nome do clienteNome do cliente',
  `sobrenome` VARCHAR(50) NOT NULL COMMENT 'Sobrenome do cliente',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE` (`cpf` ASC))
COMMENT = 'Tabela de clientes da empresa';

CREATE TABLE `sistemapedidos`.`produto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador autoincremental para a tabela produto. Chave primária.',
  `descricao` VARCHAR(45) NOT NULL COMMENT 'Descrição do produto',
  PRIMARY KEY (`id`))
COMMENT = 'Tabela de produtos';

CREATE TABLE `sistemapedidos`.`pedido` (
  `id` INT(11) NOT NULL COMMENT 'Identificador autoincremental para a tabela pedido. Chave primária.',
  `data` VARCHAR(45) NOT NULL COMMENT 'Data da realiação do pedido',
  `id_cliente` INT(11) NOT NULL COMMENT 'Código de idenficação do cliente. Chave estrangeira da tabela cliente.',
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `sistemapedidos`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabela de pedidos';

CREATE TABLE `sistemapedidos`.`item_do_pedido` (
  `id_pedido` INT(11) NOT NULL COMMENT 'Identificador referente a chave estrangeira da tabela pedido. Chave primária.',
  `id_produto` INT(11) NOT NULL COMMENT 'Identificador referente a chave estrangeira da tabela produto. Chave primária.',
  `qtdade` INT(11) NOT NULL COMMENT 'Quantidade de determinado produto no pedido ',
  PRIMARY KEY (`id_pedido`, `id_produto`),
  INDEX `fk_item_do_pedido_2_idx` (`id_produto` ASC),
  CONSTRAINT `fk_item_do_pedido_1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `sistemapedidos`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_do_pedido_2`
    FOREIGN KEY (`id_produto`)
    REFERENCES `sistemapedidos`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabela de item do pedido';
