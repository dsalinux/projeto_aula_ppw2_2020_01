-- MySQL Workbench Synchronization
-- Generated: 2020-07-07 18:34
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Danilo Souza Almeida

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(80) NULL DEFAULT NULL,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `senha` VARCHAR(256) NOT NULL,
  `data_expiracao` DATETIME NULL DEFAULT NULL,
  `data_cadastro` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`categoria` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_categoria_1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_categoria_1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `ecommerce_iftm`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`marca` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`produto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `descricao` VARCHAR(150) NOT NULL,
  `detalhes` TEXT NULL DEFAULT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  `data_desativacao` DATETIME NULL DEFAULT NULL,
  `categoria_id` INT(11) NOT NULL,
  `marca_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_categoria_idx` (`categoria_id` ASC),
  INDEX `fk_produto_marca_idx` (`marca_id` ASC),
  CONSTRAINT `fk_produto_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `ecommerce_iftm`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_marca`
    FOREIGN KEY (`marca_id`)
    REFERENCES `ecommerce_iftm`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(80) NOT NULL,
  `sexo` VARCHAR(9) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `rg` VARCHAR(14) NOT NULL,
  `orgao_expedidor` VARCHAR(10) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `senha` VARCHAR(256) NOT NULL,
  `data_nacimento` VARCHAR(45) NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_desativacao` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`endereco` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(60) NOT NULL,
  `logradouro` VARCHAR(150) NOT NULL,
  `numero` VARCHAR(20) NOT NULL,
  `complemento` VARCHAR(45) NULL DEFAULT NULL,
  `bairro` VARCHAR(80) NOT NULL,
  `cidade` VARCHAR(80) NOT NULL,
  `estado` VARCHAR(2) NOT NULL,
  `cep` VARCHAR(10) NOT NULL,
  `cliente_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_cliente_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_endereco_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ecommerce_iftm`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`venda` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) NOT NULL,
  `endereco_entrega_id` INT(11) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  `data_cancelamento` DATETIME NULL DEFAULT NULL,
  `descricao_cancelamento` VARCHAR(120) NULL DEFAULT NULL,
  `descricao_interna` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_venda_cliente_idx` (`cliente_id` ASC),
  INDEX `fk_venda_endereco_idx` (`endereco_entrega_id` ASC),
  CONSTRAINT `fk_venda_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ecommerce_iftm`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_endereco`
    FOREIGN KEY (`endereco_entrega_id`)
    REFERENCES `ecommerce_iftm`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`item_venda` (
  `produto_id` INT(11) NOT NULL,
  `venda_id` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`produto_id`, `venda_id`),
  INDEX `fk_item_venda_2_idx` (`venda_id` ASC),
  CONSTRAINT `fk_item_venda_1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommerce_iftm`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_venda_2`
    FOREIGN KEY (`venda_id`)
    REFERENCES `ecommerce_iftm`.`venda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`fluxo_caixa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `tipo_movimento` VARCHAR(15) NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `data_movimento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`imagem` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(45) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`pagamento` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `url` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`permissao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(120) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`usuario_permissao` (
  `usuario_id` INT(11) NOT NULL,
  `permissao_id` INT(11) NOT NULL,
  PRIMARY KEY (`usuario_id`, `permissao_id`),
  INDEX `fk_usuario_permissao_2_idx` (`permissao_id` ASC),
  CONSTRAINT `fk_usuario_permissao_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `ecommerce_iftm`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_permissao_2`
    FOREIGN KEY (`permissao_id`)
    REFERENCES `ecommerce_iftm`.`permissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`tag` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`produto_tag` (
  `produto_id` INT(11) NOT NULL,
  `tag_id` INT(11) NOT NULL,
  PRIMARY KEY (`produto_id`, `tag_id`),
  INDEX `fk_produto_tag_2_idx` (`tag_id` ASC),
  CONSTRAINT `fk_produto_tag_1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommerce_iftm`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_tag_2`
    FOREIGN KEY (`tag_id`)
    REFERENCES `ecommerce_iftm`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ecommerce_iftm`.`produto_imagem` (
  `produto_id` INT(11) NOT NULL,
  `imagem_id` INT(11) NOT NULL,
  PRIMARY KEY (`produto_id`, `imagem_id`),
  INDEX `fk_produto_imagem_1_idx` (`produto_id` ASC),
  CONSTRAINT `fk_produto_imagem_1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommerce_iftm`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_imagem_2`
    FOREIGN KEY (`imagem_id`)
    REFERENCES `ecommerce_iftm`.`imagem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
