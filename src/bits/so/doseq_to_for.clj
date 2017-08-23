(ns bits.so.doseq-to-for)

(def assignable-care-managers [])

(def care-managers [])
(doseq [assgnbl-care-managers assignable-care-managers]
  (let [manager-name(get-in assgnbl-care-managers [:name])
        provider-attribution(get-in assgnbl-care-managers[:alignment_supporting_facts :provider_attribution])
        asgnbl-care-managers (assoc {}:name manager-name)]
    (def care-managers (conj care-managers asgnbl-care-managers))
    (doseq [providr-attribution provider-attribution]
      (let [org-names-list(get-in providr-attribution [:org_names_list])
            asgnbl-care-managers (assoc {} :org_names_list org-names-list)]
        (def care-managers (conj care-managers asgnbl-care-managers))))))

; -----

; Assuming "assignable-care-managers" will never change, this is an appropriate use of "def".
(def assignable-care-managers [])

; I got rid of "care-managers" since it was just an empty vector

(doseq [assgnbl-care-managers assignable-care-managers]
  (let [manager-name(get-in assgnbl-care-managers [:name])
        provider-attribution(get-in assgnbl-care-managers[:alignment_supporting_facts :provider_attribution])
        asgnbl-care-managers (assoc {}:name manager-name)]
    (def care-managers (conj care-managers asgnbl-care-managers))
    (doseq [providr-attribution provider-attribution]
      (let [org-names-list(get-in providr-attribution [:org_names_list])
            asgnbl-care-managers (assoc {} :org_names_list org-names-list)]
        (def care-managers (conj care-managers asgnbl-care-managers))))))