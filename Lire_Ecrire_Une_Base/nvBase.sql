

CREATE TABLE Client (
  id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  nom varchar(100) NOT NULL,
  prenom varchar(60) NOT NULL,
  adresse varchar(200) DEFAULT NULL,
  code_postal varchar(6) DEFAULT NULL,
  ville varchar(60) DEFAULT NULL,
  pays varchar(60) DEFAULT NULL,
  email varbinary(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ind_uni_email (email)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

CREATE TABLE Espece (
  id smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  nom_courant varchar(40) NOT NULL,
  nom_latin varchar(40) NOT NULL,
  description text,
  prix decimal(7,2) unsigned DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY nom_latin (nom_latin)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE Race (
  id smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  nom varchar(40) NOT NULL,
  espece_id smallint(6) unsigned NOT NULL,
  description text,
  prix decimal(7,2) unsigned DEFAULT NULL,
  date_insertion datetime DEFAULT NULL,
  utilisateur_insertion varchar(20) DEFAULT NULL,
  date_modification datetime DEFAULT NULL,
  utilisateur_modification varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE TableDeuxCle (
  val int(4) NOT NULL DEFAULT '0',
  cle2 int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (val,cle2)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE TableDeuxPrim (
  cle1 int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (cle1)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE devise (
  devise enum('Euro','Dollar','Livre') NOT NULL,
  valeur decimal(6,5) DEFAULT NULL,
  PRIMARY KEY (devise)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE nouvTable (
  valeur int(5) NOT NULL,
  PRIMARY KEY (valeur)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE nouvelle (
  val int(4) NOT NULL DEFAULT '0',
  ref int(8) DEFAULT NULL,
  PRIMARY KEY (val),
  KEY ref (ref),
  CONSTRAINT nouvelle_ibfk_1 FOREIGN KEY (ref) REFERENCES nv2Table (vla)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE nv2Table (
  vla int(8) NOT NULL,
  PRIMARY KEY (vla)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE zetzer (
  a int(4) NOT NULL DEFAULT '0',
  b int(5) DEFAULT NULL,
  c int(2) DEFAULT NULL,
  PRIMARY KEY (a)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
