package br.com.prodama.model.cadastro.cronograma;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.prodama.enun.AnaliticoSintetico;
import br.com.prodama.enun.FormatoExecucao;
import br.com.prodama.enun.ResponsavelExecucao;
import br.com.prodama.model.cadastro.geral.Equipe;
import br.com.prodama.model.cadastro.geral.NivelEquipe;

@Entity
@Table(name="AtividadeHoraPadrao")
public class AtividadeHoraPadrao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_atividadeHoraPadrao") 
    @SequenceGenerator(name="gen_atividadeHoraPadrao", sequenceName = "seq_atividadeHoraPadrao", initialValue=1, allocationSize=1)
	@Column(name = "Codigo", nullable = false)
	private Long codigo;

	@NotEmpty
	@Basic(optional = false)
	@Column(name = "Descricao", nullable = false, length = 250)
	private String descricao;
	
	@NotEmpty
	@Basic(optional = false)
	@Column(name = "Observacao", nullable = true,length=4000)
	private String observacao;
	
	@NotNull
	@Column(name = "horaAtividade", nullable = true)
	private Long horaAtividade;

	@NotEmpty
	@Column(name = "AnaliticoSintetico", nullable = false)
	private AnaliticoSintetico analiticoSitetico;
	
	@Column(name = "FormatoExecucao", nullable = true)
	private FormatoExecucao formatoExecucao;
	
	@Column(name = "ResponsavelExecucao", nullable = true)
	private ResponsavelExecucao responsavelExecucao;
	
	@ManyToOne
	@JoinColumn(name = "CronogramaPadrao")
	private CronogramaPadrao cronogramaPadrao;	
	
	@ManyToOne(optional=true)
	@JoinColumn(name = "codigoEquipe")
	private Equipe equipe;
	
	@ManyToOne(optional=true)
	@JoinColumn(name = "codigoNivelEquipe")
	private NivelEquipe nivelEquipe;
	
	@ManyToOne
	@JoinColumn(name = "CodigoAgrupamento")
	private AgrupamentoAtidade agrupamento;
	
	@ManyToOne
	@JoinColumn(name = "AtividadeHoraPai")
	private AtividadeHoraPadrao atividadeHoraPai;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "atividadeHoraPai")
	private List<AtividadeHoraPadrao> subAtividadeHoras;

	@SuppressWarnings("deprecation")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atividadeHoraPadrao",cascade=CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<DocAtividadeHoraPadrao> listaDocAtividadesHorasPadroes;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getHoraAtividade() {
		return horaAtividade;
	}

	public void setHoraAtividade(Long horaAtividade) {
		this.horaAtividade = horaAtividade;
	}

	public AnaliticoSintetico getAnaliticoSitetico() {
		return analiticoSitetico;
	}

	public void setAnaliticoSitetico(AnaliticoSintetico analiticoSitetico) {
		this.analiticoSitetico = analiticoSitetico;
	}

	public FormatoExecucao getFormatoExecucao() {
		return formatoExecucao;
	}

	public void setFormatoExecucao(FormatoExecucao formatoExecucao) {
		this.formatoExecucao = formatoExecucao;
	}

	public ResponsavelExecucao getResponsavelExecucao() {
		return responsavelExecucao;
	}

	public void setResponsavelExecucao(ResponsavelExecucao responsavelExecucao) {
		this.responsavelExecucao = responsavelExecucao;
	}

	public CronogramaPadrao getCronogramaPadrao() {
		return cronogramaPadrao;
	}

	public void setCronogramaPadrao(CronogramaPadrao cronogramaPadrao) {
		this.cronogramaPadrao = cronogramaPadrao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public NivelEquipe getNivelEquipe() {
		return nivelEquipe;
	}

	public void setNivelEquipe(NivelEquipe nivelEquipe) {
		this.nivelEquipe = nivelEquipe;
	}

	public AgrupamentoAtidade getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(AgrupamentoAtidade agrupamento) {
		this.agrupamento = agrupamento;
	}

	public AtividadeHoraPadrao getAtividadeHoraPai() {
		return atividadeHoraPai;
	}

	public void setAtividadeHoraPai(AtividadeHoraPadrao atividadeHoraPai) {
		this.atividadeHoraPai = atividadeHoraPai;
	}

	public List<AtividadeHoraPadrao> getSubAtividadeHoras() {
		return subAtividadeHoras;
	}

	public void setSubAtividadeHoras(List<AtividadeHoraPadrao> subAtividadeHoras) {
		this.subAtividadeHoras = subAtividadeHoras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeHoraPadrao other = (AtividadeHoraPadrao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  descricao;
	}
	
	
	
	
}