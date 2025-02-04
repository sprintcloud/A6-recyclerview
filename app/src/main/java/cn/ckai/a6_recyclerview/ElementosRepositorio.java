package cn.ckai.a6_recyclerview;

import java.util.ArrayList;
import java.util.List;

public class ElementosRepositorio {

    List<Elemento> elementos = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Elemento> elementos);
    }

    ElementosRepositorio() {
        elementos.add(new Elemento("Cien años de soledad", "Esta obra maestra del realismo mágico cuenta las historias legendarias de siete generaciones de la familia Buendía y el ascenso y declive de un siglo de la pequeña ciudad de Macondo en la costa del Caribe. Refleja la historia cambiante de América Latina durante un siglo. Incorporando elementos como mitos, leyendas, cuentos populares y alusiones religiosas, despliega un mundo de imaginación magnífico.", "9788498386048", "Gabriel García Márquez", "cien_anyos_de_soledad"));
        elementos.add(new Elemento("Mundo ordinario", "Muestra panorámicamente la vida social urbana y rural contemporánea en China. Centrado en los dos hermanos, Sun Shaoping y Sun Shao'an, retrata las imágenes de numerosos personas comunes de todas las esferas de la vida en ese entonces. El trabajo y el amor, los reveses y las persecuciones, el dolor y la alegría, la vida cotidiana y los enormes conflictos sociales están intrincadamente entretejidos, mostrando profundamente los caminos arduos y tortuosos que las personas comunes han recorrido en el proceso histórico de la gran era.", "7020091384", "Lu Yao", "mundo_ordinario"));
        elementos.add(new Elemento("La luna y las seis peniques", "Basado en la vida del pintor francés del impresionismo Paul Gauguin, la obra cuenta la historia de un corredor de bolsa, Strickland, quien de repente es poseído por la magia del arte. Abandona a su esposa y a sus hijos, renuncia a lo que los demás consideran una vida próspera y feliz y se dirige a Tahití en el sur del Pacífico. Vierte el valor de su vida en el magnífico lienzo con su pincel, explorando la relación entre el ideal y la realidad, el arte y la vida.", "9787532761027", "William Somerset Maugham", "la_luna_y_las_seis_peniques"));
        elementos.add(new Elemento("Vivir", "A través de la vida de una persona común, Fugui, se cuenta cómo, en el contexto de la gran era, con cambios sociales como la Guerra Civil China, las Campañas de Tres Antis y Cinco Antis, el Gran Salto Adelante y la Revolución Cultural, su vida y su familia constantemente soportan dificultades. Al final, todos sus familiares lo dejan uno tras otro, quedando solo él, anciano, y un viejo buey juntos. Yu Hua utiliza pinceladas concisas y poderosas para que los lectores sientan la tenacidad y la fragilidad de la vida y reflexionen sobre el significado de vivir.", "9787530204321", "Yu Hua", "vivir"));
        elementos.add(new Elemento("El pequeño príncipe", "El protagonista de este libro es el Pequeño Príncipe de un planeta extraterrestre. Contado por un piloto, la historia cuenta las diversas aventuras que el Pequeño Príncipe experimenta en su viaje desde su propio planeta a la Tierra. Desde una perspectiva infantil, revela el vacío, la ceguera, la tontería y el dogma rígido de los adultos, y con un lenguaje simple e ingenuo, describe la soledad y el destino de errancia sin raíces de la humanidad. Al mismo tiempo, también expresa la crítica del autor hacia la relación centrada en el dinero y la elogio de la verdad, la bondad y la belleza.", "9787532777981", "Antoine de Saint - Exupéry", "pequenyo_principe"));
        elementos.add(new Elemento("El código Da Vinci", "La historia sigue a Robert Langdon, un simpatizante del culto a la diosa y profesor de simbología, quien se ve envuelto en una intriga después de la muerte de Jacques Saunière, el director del Museo del Louvre. Langdon y la científica索菲·奈芙 deben descifrar una serie de enigmas y pistas para desentrañar un misterio que abarca la historia, la religión y la simbología, mientras evaden a un asesino en serie y un sacerdote fanático.", "9788497341477", "Dan Brown", "codigo_da_vinci"));
    }

    List<Elemento> obtener() {
        return elementos;
    }

    void insertar(Elemento elemento, Callback callback) {
        elementos.add(elemento);
        callback.cuandoFinalice(elementos);
    }

    void eliminar(Elemento elemento, Callback callback) {
        elementos.remove(elemento);
        callback.cuandoFinalice(elementos);
    }

    void actualizar(Elemento elemento, float valoracion, Callback callback) {
        elemento.valoracion = valoracion;
        callback.cuandoFinalice(elementos);
    }
}
