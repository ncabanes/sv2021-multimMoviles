using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [SerializeField] List<Transform> wayPoints;
    int numeroSiguientePos;
    float velocidad = 1.2f;
    float distanciaCambio = 0.2f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        transform.position = Vector3.MoveTowards(
            transform.position,
            wayPoints[numeroSiguientePos].position,
            velocidad * Time.deltaTime);

        if (Vector3.Distance(transform.position,
            wayPoints[numeroSiguientePos].position)
            < distanciaCambio)
        {
            numeroSiguientePos =
                (numeroSiguientePos + 1) % wayPoints.Count;
        }
    }
}
